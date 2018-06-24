import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISconTLog } from 'app/shared/model/scon-t-log.model';
import { Principal } from 'app/core';
import { SconTLogService } from './scon-t-log.service';

@Component({
    selector: '-scon-t-log',
    templateUrl: './scon-t-log.component.html'
})
export class SconTLogComponent implements OnInit, OnDestroy {
    sconTLogs: ISconTLog[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sconTLogService: SconTLogService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sconTLogService.query().subscribe(
            (res: HttpResponse<ISconTLog[]>) => {
                this.sconTLogs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSconTLogs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISconTLog) {
        return item.id;
    }

    registerChangeInSconTLogs() {
        this.eventSubscriber = this.eventManager.subscribe('sconTLogListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
