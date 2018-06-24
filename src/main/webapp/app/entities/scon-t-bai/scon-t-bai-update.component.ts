import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISconTBai } from 'app/shared/model/scon-t-bai.model';
import { SconTBaiService } from './scon-t-bai.service';

@Component({
    selector: '-scon-t-bai-update',
    templateUrl: './scon-t-bai-update.component.html'
})
export class SconTBaiUpdateComponent implements OnInit {
    private _sconTBai: ISconTBai;
    isSaving: boolean;
    timestampDp: any;

    constructor(private sconTBaiService: SconTBaiService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sconTBai }) => {
            this.sconTBai = sconTBai;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sconTBai.id !== undefined) {
            this.subscribeToSaveResponse(this.sconTBaiService.update(this.sconTBai));
        } else {
            this.subscribeToSaveResponse(this.sconTBaiService.create(this.sconTBai));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISconTBai>>) {
        result.subscribe((res: HttpResponse<ISconTBai>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get sconTBai() {
        return this._sconTBai;
    }

    set sconTBai(sconTBai: ISconTBai) {
        this._sconTBai = sconTBai;
    }
}
