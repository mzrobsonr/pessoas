import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISconTCon } from 'app/shared/model/scon-t-con.model';
import { Principal } from 'app/core';
import { SconTConService } from './scon-t-con.service';

@Component({
    selector: '-scon-t-con',
    templateUrl: './scon-t-con.component.html'
})
export class SconTConComponent implements OnInit, OnDestroy {
    sconTCons: ISconTCon[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sconTConService: SconTConService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sconTConService.query().subscribe(
            (res: HttpResponse<ISconTCon[]>) => {
                this.sconTCons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSconTCons();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISconTCon) {
        return item.id;
    }

    registerChangeInSconTCons() {
        this.eventSubscriber = this.eventManager.subscribe('sconTConListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
