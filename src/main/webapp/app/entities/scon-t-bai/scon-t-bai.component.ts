import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISconTBai } from 'app/shared/model/scon-t-bai.model';
import { Principal } from 'app/core';
import { SconTBaiService } from './scon-t-bai.service';

@Component({
    selector: '-scon-t-bai',
    templateUrl: './scon-t-bai.component.html'
})
export class SconTBaiComponent implements OnInit, OnDestroy {
    sconTBais: ISconTBai[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sconTBaiService: SconTBaiService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sconTBaiService.query().subscribe(
            (res: HttpResponse<ISconTBai[]>) => {
                this.sconTBais = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSconTBais();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISconTBai) {
        return item.id;
    }

    registerChangeInSconTBais() {
        this.eventSubscriber = this.eventManager.subscribe('sconTBaiListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
