/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PessoasTestModule } from '../../../test.module';
import { SconTPaiDeleteDialogComponent } from 'app/entities/scon-t-pai/scon-t-pai-delete-dialog.component';
import { SconTPaiService } from 'app/entities/scon-t-pai/scon-t-pai.service';

describe('Component Tests', () => {
    describe('SconTPai Management Delete Component', () => {
        let comp: SconTPaiDeleteDialogComponent;
        let fixture: ComponentFixture<SconTPaiDeleteDialogComponent>;
        let service: SconTPaiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTPaiDeleteDialogComponent]
            })
                .overrideTemplate(SconTPaiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTPaiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTPaiService);
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
