import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISconTCon } from 'app/shared/model/scon-t-con.model';

@Component({
    selector: '-scon-t-con-detail',
    templateUrl: './scon-t-con-detail.component.html'
})
export class SconTConDetailComponent implements OnInit {
    sconTCon: ISconTCon;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTCon }) => {
            this.sconTCon = sconTCon;
        });
    }

    previousState() {
        window.history.back();
    }
}
