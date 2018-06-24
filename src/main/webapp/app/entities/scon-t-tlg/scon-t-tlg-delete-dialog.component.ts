import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISconTTlg } from 'app/shared/model/scon-t-tlg.model';
import { SconTTlgService } from './scon-t-tlg.service';

@Component({
    selector: '-scon-t-tlg-delete-dialog',
    templateUrl: './scon-t-tlg-delete-dialog.component.html'
})
export class SconTTlgDeleteDialogComponent {
    sconTTlg: ISconTTlg;

    constructor(private sconTTlgService: SconTTlgService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sconTTlgService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sconTTlgListModification',
                content: 'Deleted an sconTTlg'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-scon-t-tlg-delete-popup',
    template: ''
})
export class SconTTlgDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTTlg }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SconTTlgDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sconTTlg = sconTTlg;
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
