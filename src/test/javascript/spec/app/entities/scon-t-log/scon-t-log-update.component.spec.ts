/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTLogUpdateComponent } from 'app/entities/scon-t-log/scon-t-log-update.component';
import { SconTLogService } from 'app/entities/scon-t-log/scon-t-log.service';
import { SconTLog } from 'app/shared/model/scon-t-log.model';

describe('Component Tests', () => {
    describe('SconTLog Management Update Component', () => {
        let comp: SconTLogUpdateComponent;
        let fixture: ComponentFixture<SconTLogUpdateComponent>;
        let service: SconTLogService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTLogUpdateComponent]
            })
                .overrideTemplate(SconTLogUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTLogUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTLogService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTLog(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTLog = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTLog();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTLog = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
