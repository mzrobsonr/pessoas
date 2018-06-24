import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISconTLog } from 'app/shared/model/scon-t-log.model';
import { SconTLogService } from './scon-t-log.service';

@Component({
    selector: '-scon-t-log-delete-dialog',
    templateUrl: './scon-t-log-delete-dialog.component.html'
})
export class SconTLogDeleteDialogComponent {
    sconTLog: ISconTLog;

    constructor(private sconTLogService: SconTLogService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sconTLogService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sconTLogListModification',
                content: 'Deleted an sconTLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-scon-t-log-delete-popup',
    template: ''
})
export class SconTLogDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTLog }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SconTLogDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sconTLog = sconTLog;
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
