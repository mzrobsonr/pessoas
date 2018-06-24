import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISconTBai } from 'app/shared/model/scon-t-bai.model';
import { SconTBaiService } from './scon-t-bai.service';

@Component({
    selector: '-scon-t-bai-delete-dialog',
    templateUrl: './scon-t-bai-delete-dialog.component.html'
})
export class SconTBaiDeleteDialogComponent {
    sconTBai: ISconTBai;

    constructor(private sconTBaiService: SconTBaiService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sconTBaiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sconTBaiListModification',
                content: 'Deleted an sconTBai'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-scon-t-bai-delete-popup',
    template: ''
})
export class SconTBaiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTBai }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SconTBaiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sconTBai = sconTBai;
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
