import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISconTUfe } from 'app/shared/model/scon-t-ufe.model';
import { SconTUfeService } from './scon-t-ufe.service';

@Component({
    selector: '-scon-t-ufe-delete-dialog',
    templateUrl: './scon-t-ufe-delete-dialog.component.html'
})
export class SconTUfeDeleteDialogComponent {
    sconTUfe: ISconTUfe;

    constructor(private sconTUfeService: SconTUfeService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sconTUfeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sconTUfeListModification',
                content: 'Deleted an sconTUfe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-scon-t-ufe-delete-popup',
    template: ''
})
export class SconTUfeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTUfe }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SconTUfeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sconTUfe = sconTUfe;
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
