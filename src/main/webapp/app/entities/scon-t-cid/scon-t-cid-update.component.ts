import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISconTCid } from 'app/shared/model/scon-t-cid.model';
import { SconTCidService } from './scon-t-cid.service';
import { ISconTUfe } from 'app/shared/model/scon-t-ufe.model';
import { SconTUfeService } from 'app/entities/scon-t-ufe';

@Component({
    selector: '-scon-t-cid-update',
    templateUrl: './scon-t-cid-update.component.html'
})
export class SconTCidUpdateComponent implements OnInit {
    private _sconTCid: ISconTCid;
    isSaving: boolean;

    scontufes: ISconTUfe[];
    timestampDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sconTCidService: SconTCidService,
        private sconTUfeService: SconTUfeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sconTCid }) => {
            this.sconTCid = sconTCid;
        });
        this.sconTUfeService.query().subscribe(
            (res: HttpResponse<ISconTUfe[]>) => {
                this.scontufes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sconTCid.id !== undefined) {
            this.subscribeToSaveResponse(this.sconTCidService.update(this.sconTCid));
        } else {
            this.subscribeToSaveResponse(this.sconTCidService.create(this.sconTCid));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISconTCid>>) {
        result.subscribe((res: HttpResponse<ISconTCid>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSconTUfeById(index: number, item: ISconTUfe) {
        return item.id;
    }
    get sconTCid() {
        return this._sconTCid;
    }

    set sconTCid(sconTCid: ISconTCid) {
        this._sconTCid = sconTCid;
    }
}
