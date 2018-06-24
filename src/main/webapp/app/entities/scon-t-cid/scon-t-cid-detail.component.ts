import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISconTCid } from 'app/shared/model/scon-t-cid.model';

@Component({
    selector: '-scon-t-cid-detail',
    templateUrl: './scon-t-cid-detail.component.html'
})
export class SconTCidDetailComponent implements OnInit {
    sconTCid: ISconTCid;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTCid }) => {
            this.sconTCid = sconTCid;
        });
    }

    previousState() {
        window.history.back();
    }
}
