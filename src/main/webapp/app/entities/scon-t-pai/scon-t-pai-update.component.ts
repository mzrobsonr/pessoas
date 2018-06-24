import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISconTPai } from 'app/shared/model/scon-t-pai.model';
import { SconTPaiService } from './scon-t-pai.service';

@Component({
    selector: '-scon-t-pai-update',
    templateUrl: './scon-t-pai-update.component.html'
})
export class SconTPaiUpdateComponent implements OnInit {
    private _sconTPai: ISconTPai;
    isSaving: boolean;
    timestampDp: any;

    constructor(private sconTPaiService: SconTPaiService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sconTPai }) => {
            this.sconTPai = sconTPai;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sconTPai.id !== undefined) {
            this.subscribeToSaveResponse(this.sconTPaiService.update(this.sconTPai));
        } else {
            this.subscribeToSaveResponse(this.sconTPaiService.create(this.sconTPai));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISconTPai>>) {
        result.subscribe((res: HttpResponse<ISconTPai>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get sconTPai() {
        return this._sconTPai;
    }

    set sconTPai(sconTPai: ISconTPai) {
        this._sconTPai = sconTPai;
    }
}
