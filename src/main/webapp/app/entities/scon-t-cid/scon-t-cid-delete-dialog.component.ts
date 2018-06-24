import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISconTCid } from 'app/shared/model/scon-t-cid.model';
import { SconTCidService } from './scon-t-cid.service';

@Component({
    selector: '-scon-t-cid-delete-dialog',
    templateUrl: './scon-t-cid-delete-dialog.component.html'
})
export class SconTCidDeleteDialogComponent {
    sconTCid: ISconTCid;

    constructor(private sconTCidService: SconTCidService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sconTCidService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sconTCidListModification',
                content: 'Deleted an sconTCid'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-scon-t-cid-delete-popup',
    template: ''
})
export class SconTCidDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTCid }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SconTCidDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sconTCid = sconTCid;
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
