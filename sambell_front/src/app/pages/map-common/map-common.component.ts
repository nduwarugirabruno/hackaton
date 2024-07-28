import { Component, OnInit } from '@angular/core';
import mapboxgl from 'mapbox-gl';

@Component({
    selector: 'app-map-common',
    standalone: true,
    imports: [],
    templateUrl: './map-common.component.html',
    styleUrl: './map-common.component.scss',
})
export class MapCommonComponent implements OnInit {
    ngOnInit(): void {
        mapboxgl.accessToken = 'YOUR_MAPBOX_ACCESS_TOKEN';
        const map = new mapboxgl.Map({
            container: 'map', // container ID
            style: 'mapbox://styles/mapbox/streets-v12', // style URL
            center: [-74.5, 40], // starting position [lng, lat]
            zoom: 9, // starting zoom
        });
    }
}
