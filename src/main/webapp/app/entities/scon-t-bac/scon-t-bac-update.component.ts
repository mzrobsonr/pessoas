import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISconTBac } from 'app/shared/model/scon-t-bac.model';
import { SconTBacService } from './scon-t-bac.service';
import { ISconTCid } from 'app/shared/model/scon-t-cid.model';
import { SconTCidService } from 'app/entities/scon-t-cid';
import { ISconTBai } from 'app/shared/model/scon-t-bai.model';
import { SconTBaiService } from 'app/entities/scon-t-bai';

@Component({
    selector: '-scon-t-bac-update',
    templateUrl: './scon-t-bac-update.component.html'
})
export class SconTBacUpdateComponent implements OnInit {
    private _sconTBac: ISconTBac;
    isSaving: boolean;

    scontcids: ISconTCid[];

    scontbais: ISconTBai[];
    timestampDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sconTBacService: SconTBacService,
        private sconTCidService: SconTCidService,
        private sconTBaiService: SconTBaiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sconTBac }) => {
            this.sconTBac = sconTBac;
        });
        this.sconTCidService.query().subscribe(
            (res: HttpResponse<ISconTCid[]>) => {
                this.scontcids = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sconTBaiService.query().subscribe(
            (res: HttpResponse<ISconTBai[]>) => {
                this.scontbais = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sconTBac.id !== undefined) {
            this.subscribeToSaveResponse(this.sconTBacService.update(this.sconTBac));
        } else {
            this.subscribeToSaveResponse(this.sconTBacService.create(this.sconTBac));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISconTBac>>) {
        result.subscribe((res: HttpResponse<ISconTBac>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSconTCidById(index: number, item: ISconTCid) {
        return item.id;
    }

    trackSconTBaiById(index: number, item: ISconTBai) {
        return item.id;
    }
    get sconTBac() {
        return this._sconTBac;
    }

    set sconTBac(sconTBac: ISconTBac) {
        this._sconTBac = sconTBac;
    }
}
