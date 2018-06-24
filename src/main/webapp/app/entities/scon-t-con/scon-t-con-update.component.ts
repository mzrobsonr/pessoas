import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISconTCon } from 'app/shared/model/scon-t-con.model';
import { SconTConService } from './scon-t-con.service';
import { ISconTLgb } from 'app/shared/model/scon-t-lgb.model';
import { SconTLgbService } from 'app/entities/scon-t-lgb';

@Component({
    selector: '-scon-t-con-update',
    templateUrl: './scon-t-con-update.component.html'
})
export class SconTConUpdateComponent implements OnInit {
    private _sconTCon: ISconTCon;
    isSaving: boolean;

    idlgbcs: ISconTLgb[];

    idlgbrs: ISconTLgb[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private sconTConService: SconTConService,
        private sconTLgbService: SconTLgbService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sconTCon }) => {
            this.sconTCon = sconTCon;
        });
        this.sconTLgbService.query({ filter: 'scontcon-is-null' }).subscribe(
            (res: HttpResponse<ISconTLgb[]>) => {
                if (!this.sconTCon.idlgbc || !this.sconTCon.idlgbc.id) {
                    this.idlgbcs = res.body;
                } else {
                    this.sconTLgbService.find(this.sconTCon.idlgbc.id).subscribe(
                        (subRes: HttpResponse<ISconTLgb>) => {
                            this.idlgbcs = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sconTLgbService.query({ filter: 'scontcon-is-null' }).subscribe(
            (res: HttpResponse<ISconTLgb[]>) => {
                if (!this.sconTCon.idlgbr || !this.sconTCon.idlgbr.id) {
                    this.idlgbrs = res.body;
                } else {
                    this.sconTLgbService.find(this.sconTCon.idlgbr.id).subscribe(
                        (subRes: HttpResponse<ISconTLgb>) => {
                            this.idlgbrs = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sconTCon.id !== undefined) {
            this.subscribeToSaveResponse(this.sconTConService.update(this.sconTCon));
        } else {
            this.subscribeToSaveResponse(this.sconTConService.create(this.sconTCon));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISconTCon>>) {
        result.subscribe((res: HttpResponse<ISconTCon>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSconTLgbById(index: number, item: ISconTLgb) {
        return item.id;
    }
    get sconTCon() {
        return this._sconTCon;
    }

    set sconTCon(sconTCon: ISconTCon) {
        this._sconTCon = sconTCon;
    }
}
