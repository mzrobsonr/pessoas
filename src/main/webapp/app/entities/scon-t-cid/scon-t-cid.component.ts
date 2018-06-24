import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISconTCid } from 'app/shared/model/scon-t-cid.model';
import { Principal } from 'app/core';
import { SconTCidService } from './scon-t-cid.service';

@Component({
    selector: '-scon-t-cid',
    templateUrl: './scon-t-cid.component.html'
})
export class SconTCidComponent implements OnInit, OnDestroy {
    sconTCids: ISconTCid[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sconTCidService: SconTCidService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sconTCidService.query().subscribe(
            (res: HttpResponse<ISconTCid[]>) => {
                this.sconTCids = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSconTCids();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISconTCid) {
        return item.id;
    }

    registerChangeInSconTCids() {
        this.eventSubscriber = this.eventManager.subscribe('sconTCidListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
