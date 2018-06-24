import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISconTBac } from 'app/shared/model/scon-t-bac.model';
import { Principal } from 'app/core';
import { SconTBacService } from './scon-t-bac.service';

@Component({
    selector: '-scon-t-bac',
    templateUrl: './scon-t-bac.component.html'
})
export class SconTBacComponent implements OnInit, OnDestroy {
    sconTBacs: ISconTBac[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sconTBacService: SconTBacService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sconTBacService.query().subscribe(
            (res: HttpResponse<ISconTBac[]>) => {
                this.sconTBacs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSconTBacs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISconTBac) {
        return item.id;
    }

    registerChangeInSconTBacs() {
        this.eventSubscriber = this.eventManager.subscribe('sconTBacListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
