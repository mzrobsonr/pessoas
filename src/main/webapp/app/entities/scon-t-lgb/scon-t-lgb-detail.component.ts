import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISconTLgb } from 'app/shared/model/scon-t-lgb.model';

@Component({
    selector: '-scon-t-lgb-detail',
    templateUrl: './scon-t-lgb-detail.component.html'
})
export class SconTLgbDetailComponent implements OnInit {
    sconTLgb: ISconTLgb;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTLgb }) => {
            this.sconTLgb = sconTLgb;
        });
    }

    previousState() {
        window.history.back();
    }
}
