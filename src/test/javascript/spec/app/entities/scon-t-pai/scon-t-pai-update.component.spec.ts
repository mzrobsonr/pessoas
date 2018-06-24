/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTPaiUpdateComponent } from 'app/entities/scon-t-pai/scon-t-pai-update.component';
import { SconTPaiService } from 'app/entities/scon-t-pai/scon-t-pai.service';
import { SconTPai } from 'app/shared/model/scon-t-pai.model';

describe('Component Tests', () => {
    describe('SconTPai Management Update Component', () => {
        let comp: SconTPaiUpdateComponent;
        let fixture: ComponentFixture<SconTPaiUpdateComponent>;
        let service: SconTPaiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTPaiUpdateComponent]
            })
                .overrideTemplate(SconTPaiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTPaiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTPaiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTPai(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTPai = entity;
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
                    const entity = new SconTPai();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTPai = entity;
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
