/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTUfeUpdateComponent } from 'app/entities/scon-t-ufe/scon-t-ufe-update.component';
import { SconTUfeService } from 'app/entities/scon-t-ufe/scon-t-ufe.service';
import { SconTUfe } from 'app/shared/model/scon-t-ufe.model';

describe('Component Tests', () => {
    describe('SconTUfe Management Update Component', () => {
        let comp: SconTUfeUpdateComponent;
        let fixture: ComponentFixture<SconTUfeUpdateComponent>;
        let service: SconTUfeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTUfeUpdateComponent]
            })
                .overrideTemplate(SconTUfeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTUfeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTUfeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTUfe(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTUfe = entity;
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
                    const entity = new SconTUfe();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTUfe = entity;
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
