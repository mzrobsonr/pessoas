import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISconTTlg } from 'app/shared/model/scon-t-tlg.model';

@Component({
    selector: '-scon-t-tlg-detail',
    templateUrl: './scon-t-tlg-detail.component.html'
})
export class SconTTlgDetailComponent implements OnInit {
    sconTTlg: ISconTTlg;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTTlg }) => {
            this.sconTTlg = sconTTlg;
        });
    }

    previousState() {
        window.history.back();
    }
}
