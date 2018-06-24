/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PessoasTestModule } from '../../../test.module';
import { SconTPaiComponent } from 'app/entities/scon-t-pai/scon-t-pai.component';
import { SconTPaiService } from 'app/entities/scon-t-pai/scon-t-pai.service';
import { SconTPai } from 'app/shared/model/scon-t-pai.model';

describe('Component Tests', () => {
    describe('SconTPai Management Component', () => {
        let comp: SconTPaiComponent;
        let fixture: ComponentFixture<SconTPaiComponent>;
        let service: SconTPaiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTPaiComponent],
                providers: []
            })
                .overrideTemplate(SconTPaiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTPaiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTPaiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SconTPai(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sconTPais[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
