import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISconTUfe } from 'app/shared/model/scon-t-ufe.model';
import { Principal } from 'app/core';
import { SconTUfeService } from './scon-t-ufe.service';

@Component({
    selector: '-scon-t-ufe',
    templateUrl: './scon-t-ufe.component.html'
})
export class SconTUfeComponent implements OnInit, OnDestroy {
    sconTUfes: ISconTUfe[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sconTUfeService: SconTUfeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sconTUfeService.query().subscribe(
            (res: HttpResponse<ISconTUfe[]>) => {
                this.sconTUfes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSconTUfes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISconTUfe) {
        return item.id;
    }

    registerChangeInSconTUfes() {
        this.eventSubscriber = this.eventManager.subscribe('sconTUfeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
