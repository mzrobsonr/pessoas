/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTBacUpdateComponent } from 'app/entities/scon-t-bac/scon-t-bac-update.component';
import { SconTBacService } from 'app/entities/scon-t-bac/scon-t-bac.service';
import { SconTBac } from 'app/shared/model/scon-t-bac.model';

describe('Component Tests', () => {
    describe('SconTBac Management Update Component', () => {
        let comp: SconTBacUpdateComponent;
        let fixture: ComponentFixture<SconTBacUpdateComponent>;
        let service: SconTBacService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTBacUpdateComponent]
            })
                .overrideTemplate(SconTBacUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTBacUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTBacService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTBac(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTBac = entity;
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
                    const entity = new SconTBac();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTBac = entity;
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
