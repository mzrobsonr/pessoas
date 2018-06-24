import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISconTBac } from 'app/shared/model/scon-t-bac.model';
import { SconTBacService } from './scon-t-bac.service';

@Component({
    selector: '-scon-t-bac-delete-dialog',
    templateUrl: './scon-t-bac-delete-dialog.component.html'
})
export class SconTBacDeleteDialogComponent {
    sconTBac: ISconTBac;

    constructor(private sconTBacService: SconTBacService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sconTBacService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sconTBacListModification',
                content: 'Deleted an sconTBac'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-scon-t-bac-delete-popup',
    template: ''
})
export class SconTBacDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTBac }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SconTBacDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sconTBac = sconTBac;
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
