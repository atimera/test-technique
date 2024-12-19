import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {CoordonneesGps, CoordonneesGpsDto} from '../interfaces/coordonnees-gps';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CoordonneesGpsService {
  private apiBaseUrl = '/coordonnees-gps';
  http = inject(HttpClient);

  constructor() {}

  lister(): Observable<any> {
    return this.http.get(`${this.apiBaseUrl}`);
  }

  ajouterCoordonneesGps(latitude: number, longitude: number): Observable<any> {
    return this.http.post(`${this.apiBaseUrl}`,  {
      latitude: latitude,
      longitude: longitude
    });
  }

  calculerDistance(pointA: CoordonneesGpsDto, pointB: CoordonneesGpsDto): Observable<any> {
    return this.http.post(`${this.apiBaseUrl}/distance`,  {
      pointA: pointA,
      pointB: pointB
    });
  }

  supprimerCoordonneesGps(id: number): Observable<any> {
    return this.http.delete(`${this.apiBaseUrl}/` + id);
  }

}
