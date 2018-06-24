import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISconTLgb } from 'app/shared/model/scon-t-lgb.model';
import { Principal } from 'app/core';
import { SconTLgbService } from './scon-t-lgb.service';

@Component({
    selector: '-scon-t-lgb',
    templateUrl: './scon-t-lgb.component.html'
})
export class SconTLgbComponent implements OnInit, OnDestroy {
    sconTLgbs: ISconTLgb[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sconTLgbService: SconTLgbService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sconTLgbService.query().subscribe(
            (res: HttpResponse<ISconTLgb[]>) => {
                this.sconTLgbs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSconTLgbs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISconTLgb) {
        return item.id;
    }

    registerChangeInSconTLgbs() {
        this.eventSubscriber = this.eventManager.subscribe('sconTLgbListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
