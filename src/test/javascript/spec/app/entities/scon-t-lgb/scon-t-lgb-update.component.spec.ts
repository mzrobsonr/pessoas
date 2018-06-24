/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTLgbUpdateComponent } from 'app/entities/scon-t-lgb/scon-t-lgb-update.component';
import { SconTLgbService } from 'app/entities/scon-t-lgb/scon-t-lgb.service';
import { SconTLgb } from 'app/shared/model/scon-t-lgb.model';

describe('Component Tests', () => {
    describe('SconTLgb Management Update Component', () => {
        let comp: SconTLgbUpdateComponent;
        let fixture: ComponentFixture<SconTLgbUpdateComponent>;
        let service: SconTLgbService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTLgbUpdateComponent]
            })
                .overrideTemplate(SconTLgbUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTLgbUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTLgbService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTLgb(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTLgb = entity;
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
                    const entity = new SconTLgb();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTLgb = entity;
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
