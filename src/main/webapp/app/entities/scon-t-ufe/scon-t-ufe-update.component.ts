import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISconTUfe } from 'app/shared/model/scon-t-ufe.model';
import { SconTUfeService } from './scon-t-ufe.service';
import { ISconTPai } from 'app/shared/model/scon-t-pai.model';
import { SconTPaiService } from 'app/entities/scon-t-pai';

@Component({
    selector: '-scon-t-ufe-update',
    templateUrl: './scon-t-ufe-update.component.html'
})
export class SconTUfeUpdateComponent implements OnInit {
    private _sconTUfe: ISconTUfe;
    isSaving: boolean;

    scontpais: ISconTPai[];
    timestampDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sconTUfeService: SconTUfeService,
        private sconTPaiService: SconTPaiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sconTUfe }) => {
            this.sconTUfe = sconTUfe;
        });
        this.sconTPaiService.query().subscribe(
            (res: HttpResponse<ISconTPai[]>) => {
                this.scontpais = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sconTUfe.id !== undefined) {
            this.subscribeToSaveResponse(this.sconTUfeService.update(this.sconTUfe));
        } else {
            this.subscribeToSaveResponse(this.sconTUfeService.create(this.sconTUfe));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISconTUfe>>) {
        result.subscribe((res: HttpResponse<ISconTUfe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSconTPaiById(index: number, item: ISconTPai) {
        return item.id;
    }
    get sconTUfe() {
        return this._sconTUfe;
    }

    set sconTUfe(sconTUfe: ISconTUfe) {
        this._sconTUfe = sconTUfe;
    }
}
