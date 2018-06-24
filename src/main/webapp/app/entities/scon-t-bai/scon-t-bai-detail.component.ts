import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISconTBai } from 'app/shared/model/scon-t-bai.model';

@Component({
    selector: '-scon-t-bai-detail',
    templateUrl: './scon-t-bai-detail.component.html'
})
export class SconTBaiDetailComponent implements OnInit {
    sconTBai: ISconTBai;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTBai }) => {
            this.sconTBai = sconTBai;
        });
    }

    previousState() {
        window.history.back();
    }
}
