/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PessoasTestModule } from '../../../test.module';
import { SconTCidDeleteDialogComponent } from 'app/entities/scon-t-cid/scon-t-cid-delete-dialog.component';
import { SconTCidService } from 'app/entities/scon-t-cid/scon-t-cid.service';

describe('Component Tests', () => {
    describe('SconTCid Management Delete Component', () => {
        let comp: SconTCidDeleteDialogComponent;
        let fixture: ComponentFixture<SconTCidDeleteDialogComponent>;
        let service: SconTCidService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTCidDeleteDialogComponent]
            })
                .overrideTemplate(SconTCidDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTCidDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTCidService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
