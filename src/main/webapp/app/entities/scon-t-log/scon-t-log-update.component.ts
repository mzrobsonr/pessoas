import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISconTLog } from 'app/shared/model/scon-t-log.model';
import { SconTLogService } from './scon-t-log.service';
import { ISconTTlg } from 'app/shared/model/scon-t-tlg.model';
import { SconTTlgService } from 'app/entities/scon-t-tlg';

@Component({
    selector: '-scon-t-log-update',
    templateUrl: './scon-t-log-update.component.html'
})
export class SconTLogUpdateComponent implements OnInit {
    private _sconTLog: ISconTLog;
    isSaving: boolean;

    sconttlgs: ISconTTlg[];
    timestampDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sconTLogService: SconTLogService,
        private sconTTlgService: SconTTlgService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sconTLog }) => {
            this.sconTLog = sconTLog;
        });
        this.sconTTlgService.query().subscribe(
            (res: HttpResponse<ISconTTlg[]>) => {
                this.sconttlgs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sconTLog.id !== undefined) {
            this.subscribeToSaveResponse(this.sconTLogService.update(this.sconTLog));
        } else {
            this.subscribeToSaveResponse(this.sconTLogService.create(this.sconTLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISconTLog>>) {
        result.subscribe((res: HttpResponse<ISconTLog>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSconTTlgById(index: number, item: ISconTTlg) {
        return item.id;
    }
    get sconTLog() {
        return this._sconTLog;
    }

    set sconTLog(sconTLog: ISconTLog) {
        this._sconTLog = sconTLog;
    }
}
