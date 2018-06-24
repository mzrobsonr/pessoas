import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISconTPai } from 'app/shared/model/scon-t-pai.model';
import { SconTPaiService } from './scon-t-pai.service';

@Component({
    selector: '-scon-t-pai-delete-dialog',
    templateUrl: './scon-t-pai-delete-dialog.component.html'
})
export class SconTPaiDeleteDialogComponent {
    sconTPai: ISconTPai;

    constructor(private sconTPaiService: SconTPaiService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sconTPaiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sconTPaiListModification',
                content: 'Deleted an sconTPai'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-scon-t-pai-delete-popup',
    template: ''
})
export class SconTPaiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sconTPai }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SconTPaiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sconTPai = sconTPai;
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
