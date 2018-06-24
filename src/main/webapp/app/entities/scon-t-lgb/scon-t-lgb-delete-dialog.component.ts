import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISconTLgb } from 'app/shared/model/scon-t-lgb.model';
import { SconTLgbService } from './scon-t-lgb.service';

@Component({
    selector: '-scon-t-lgb-delete-dialog',
    templateUrl: './scon-t-lgb-delete-dialog.component.html'
})
export class SconTLgbDeleteDialogComponent {
    sconTLgb: ISconTLgb;

    constructor(private sconTLgbService: SconTLgbService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sconTLgbService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sconTLgbListModification',
                content: 'Deleted an sconTLgb'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-scon-t-lgb-delete-popup',
    template: ''
})
export class SconTLgbDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTLgb }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SconTLgbDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sconTLgb = sconTLgb;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
