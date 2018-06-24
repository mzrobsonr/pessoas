/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PessoasTestModule } from '../../../test.module';
import { SconTUfeDeleteDialogComponent } from 'app/entities/scon-t-ufe/scon-t-ufe-delete-dialog.component';
import { SconTUfeService } from 'app/entities/scon-t-ufe/scon-t-ufe.service';

describe('Component Tests', () => {
    describe('SconTUfe Management Delete Component', () => {
        let comp: SconTUfeDeleteDialogComponent;
        let fixture: ComponentFixture<SconTUfeDeleteDialogComponent>;
        let service: SconTUfeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTUfeDeleteDialogComponent]
            })
                .overrideTemplate(SconTUfeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTUfeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTUfeService);
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
