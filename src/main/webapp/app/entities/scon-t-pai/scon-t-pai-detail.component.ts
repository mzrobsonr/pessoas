import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISconTPai } from 'app/shared/model/scon-t-pai.model';

@Component({
    selector: '-scon-t-pai-detail',
    templateUrl: './scon-t-pai-detail.component.html'
})
export class SconTPaiDetailComponent implements OnInit {
    sconTPai: ISconTPai;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTPai }) => {
            this.sconTPai = sconTPai;
        });
    }

    previousState() {
        window.history.back();
    }
}
