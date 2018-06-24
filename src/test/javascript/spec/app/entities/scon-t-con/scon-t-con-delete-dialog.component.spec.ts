/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PessoasTestModule } from '../../../test.module';
import { SconTConDeleteDialogComponent } from 'app/entities/scon-t-con/scon-t-con-delete-dialog.component';
import { SconTConService } from 'app/entities/scon-t-con/scon-t-con.service';

describe('Component Tests', () => {
    describe('SconTCon Management Delete Component', () => {
        let comp: SconTConDeleteDialogComponent;
        let fixture: ComponentFixture<SconTConDeleteDialogComponent>;
        let service: SconTConService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTConDeleteDialogComponent]
            })
                .overrideTemplate(SconTConDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTConDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTConService);
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
