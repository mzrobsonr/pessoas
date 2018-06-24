/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PessoasTestModule } from '../../../test.module';
import { SconTBacDeleteDialogComponent } from 'app/entities/scon-t-bac/scon-t-bac-delete-dialog.component';
import { SconTBacService } from 'app/entities/scon-t-bac/scon-t-bac.service';

describe('Component Tests', () => {
    describe('SconTBac Management Delete Component', () => {
        let comp: SconTBacDeleteDialogComponent;
        let fixture: ComponentFixture<SconTBacDeleteDialogComponent>;
        let service: SconTBacService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTBacDeleteDialogComponent]
            })
                .overrideTemplate(SconTBacDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTBacDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTBacService);
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
