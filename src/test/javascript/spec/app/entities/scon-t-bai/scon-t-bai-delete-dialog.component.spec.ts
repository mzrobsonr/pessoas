/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PessoasTestModule } from '../../../test.module';
import { SconTBaiDeleteDialogComponent } from 'app/entities/scon-t-bai/scon-t-bai-delete-dialog.component';
import { SconTBaiService } from 'app/entities/scon-t-bai/scon-t-bai.service';

describe('Component Tests', () => {
    describe('SconTBai Management Delete Component', () => {
        let comp: SconTBaiDeleteDialogComponent;
        let fixture: ComponentFixture<SconTBaiDeleteDialogComponent>;
        let service: SconTBaiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTBaiDeleteDialogComponent]
            })
                .overrideTemplate(SconTBaiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTBaiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTBaiService);
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
