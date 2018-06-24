import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISconTLgb } from 'app/shared/model/scon-t-lgb.model';
import { SconTLgbService } from './scon-t-lgb.service';
import { ISconTBac } from 'app/shared/model/scon-t-bac.model';
import { SconTBacService } from 'app/entities/scon-t-bac';
import { ISconTLog } from 'app/shared/model/scon-t-log.model';
import { SconTLogService } from 'app/entities/scon-t-log';

@Component({
    selector: '-scon-t-lgb-update',
    templateUrl: './scon-t-lgb-update.component.html'
})
export class SconTLgbUpdateComponent implements OnInit {
    private _sconTLgb: ISconTLgb;
    isSaving: boolean;

    scontbacs: ISconTBac[];

    scontlogs: ISconTLog[];
    timestampDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sconTLgbService: SconTLgbService,
        private sconTBacService: SconTBacService,
        private sconTLogService: SconTLogService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sconTLgb }) => {
            this.sconTLgb = sconTLgb;
        });
        this.sconTBacService.query().subscribe(
            (res: HttpResponse<ISconTBac[]>) => {
                this.scontbacs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sconTLogService.query().subscribe(
            (res: HttpResponse<ISconTLog[]>) => {
                this.scontlogs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sconTLgb.id !== undefined) {
            this.subscribeToSaveResponse(this.sconTLgbService.update(this.sconTLgb));
        } else {
            this.subscribeToSaveResponse(this.sconTLgbService.create(this.sconTLgb));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISconTLgb>>) {
        result.subscribe((res: HttpResponse<ISconTLgb>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSconTBacById(index: number, item: ISconTBac) {
        return item.id;
    }

    trackSconTLogById(index: number, item: ISconTLog) {
        return item.id;
    }
    get sconTLgb() {
        return this._sconTLgb;
    }

    set sconTLgb(sconTLgb: ISconTLgb) {
        this._sconTLgb = sconTLgb;
    }
}
