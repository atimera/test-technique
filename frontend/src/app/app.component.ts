import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CoordonneesGpsService } from './services/coordonnees-gps.service';
import {CoordonneesGps, CoordonneesGpsDto} from './interfaces/coordonnees-gps';
import { CommonModule } from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  title = 'Coordonnées GPS';
  distance!: number;
  listeCoordonneesGps!: CoordonneesGps[];

  coordService = inject(CoordonneesGpsService);

  ajoutCoordonneesGpsForm = new FormGroup({
    latitude: new FormControl('', [Validators.required]),
    longitude: new FormControl('', [Validators.required]),
  })

  calculDistanceForm = new FormGroup({
    latitudePointA: new FormControl(),
    longitudePointA: new FormControl(),
    latitudePointB: new FormControl(),
    longitudePointB: new FormControl()
  });

  ngOnInit(): void {
    this.miseAJourListeCoordonneesGps();
  }

  onAjouterCoordonneesGps() {
    if(this.ajoutCoordonneesGpsForm.valid) {
      const lat = +this.ajoutCoordonneesGpsForm.value.latitude!;
      const lon = +this.ajoutCoordonneesGpsForm.value.longitude!;
      this.coordService.ajouterCoordonneesGps(lat, lon).subscribe({
        next: (reponse) => {
          this.miseAJourListeCoordonneesGps();
        },
        error: (err) => {
          this.logMessage(err.message)
        }
      })
    }
  }

  miseAJourListeCoordonneesGps(){
    this.coordService.lister().subscribe({
      next: (reponse) => {
        console.log("responde.data.listeCoordonneesGps", reponse.data.listeCoordonneesGps);
        if(reponse.data?.listeCoordonneesGps) {
          this.listeCoordonneesGps = reponse.data?.listeCoordonneesGps;
        }
      },
      error: (err) => {
        this.logMessage(err.message)
      },
    });
  }

  onSupprimerCoordonneesGps(id: number) {
    this.coordService.supprimerCoordonneesGps(id).subscribe({
      next: (reponse) => {
        this.miseAJourListeCoordonneesGps();
      }, error: (err) => {
        this.logMessage(err.message);
      }
    });
  }

  onCalculerDistance() {
    let pointA : CoordonneesGpsDto;
    let pointB : CoordonneesGpsDto;
    if(this.calculDistanceForm.invalid) {
      return;
    }
    pointA = {
      latitude: this.calculDistanceForm.value.latitudePointA,
      longitude: this.calculDistanceForm.value.latitudePointA
    }
    pointB = {
      latitude: this.calculDistanceForm.value.latitudePointB,
      longitude: this.calculDistanceForm.value.latitudePointB
    }
    this.coordService.calculerDistance(pointA, pointB).subscribe({
      next: (reponse) => {
        console.log(reponse.data);
        this.distance = reponse.data.distance;
      },
      error: (err) => {
        this.logMessage(err.message);
      }
    })
  }

  logMessage(error: any  ){
    console.log('Erreur survenue', error);
    alert("Une erreur est survenue. Voir les logs pour plus de détail.");
  }
}
