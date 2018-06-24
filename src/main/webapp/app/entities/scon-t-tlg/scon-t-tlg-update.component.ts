import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISconTTlg } from 'app/shared/model/scon-t-tlg.model';
import { SconTTlgService } from './scon-t-tlg.service';

@Component({
    selector: '-scon-t-tlg-update',
    templateUrl: './scon-t-tlg-update.component.html'
})
export class SconTTlgUpdateComponent implements OnInit {
    private _sconTTlg: ISconTTlg;
    isSaving: boolean;
    timestampDp: any;

    constructor(private sconTTlgService: SconTTlgService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sconTTlg }) => {
            this.sconTTlg = sconTTlg;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sconTTlg.id !== undefined) {
            this.subscribeToSaveResponse(this.sconTTlgService.update(this.sconTTlg));
        } else {
            this.subscribeToSaveResponse(this.sconTTlgService.create(this.sconTTlg));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISconTTlg>>) {
        result.subscribe((res: HttpResponse<ISconTTlg>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get sconTTlg() {
        return this._sconTTlg;
    }

    set sconTTlg(sconTTlg: ISconTTlg) {
        this._sconTTlg = sconTTlg;
    }
}
