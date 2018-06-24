import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISconTUfe } from 'app/shared/model/scon-t-ufe.model';

@Component({
    selector: '-scon-t-ufe-detail',
    templateUrl: './scon-t-ufe-detail.component.html'
})
export class SconTUfeDetailComponent implements OnInit {
    sconTUfe: ISconTUfe;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTUfe }) => {
            this.sconTUfe = sconTUfe;
        });
    }

    previousState() {
        window.history.back();
    }
}
