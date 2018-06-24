import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISconTCon } from 'app/shared/model/scon-t-con.model';
import { SconTConService } from './scon-t-con.service';

@Component({
    selector: '-scon-t-con-delete-dialog',
    templateUrl: './scon-t-con-delete-dialog.component.html'
})
export class SconTConDeleteDialogComponent {
    sconTCon: ISconTCon;

    constructor(private sconTConService: SconTConService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sconTConService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sconTConListModification',
                content: 'Deleted an sconTCon'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-scon-t-con-delete-popup',
    template: ''
})
export class SconTConDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTCon }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SconTConDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sconTCon = sconTCon;
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
