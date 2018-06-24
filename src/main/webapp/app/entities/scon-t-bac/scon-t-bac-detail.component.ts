import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISconTBac } from 'app/shared/model/scon-t-bac.model';

@Component({
    selector: '-scon-t-bac-detail',
    templateUrl: './scon-t-bac-detail.component.html'
})
export class SconTBacDetailComponent implements OnInit {
    sconTBac: ISconTBac;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTBac }) => {
            this.sconTBac = sconTBac;
        });
    }

    previousState() {
        window.history.back();
    }
}
