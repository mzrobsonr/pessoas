import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISconTLog } from 'app/shared/model/scon-t-log.model';

@Component({
    selector: '-scon-t-log-detail',
    templateUrl: './scon-t-log-detail.component.html'
})
export class SconTLogDetailComponent implements OnInit {
    sconTLog: ISconTLog;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTLog }) => {
            this.sconTLog = sconTLog;
        });
    }

    previousState() {
        window.history.back();
    }
}
