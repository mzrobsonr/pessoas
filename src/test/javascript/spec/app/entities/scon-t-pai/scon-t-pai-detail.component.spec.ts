/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTPaiDetailComponent } from 'app/entities/scon-t-pai/scon-t-pai-detail.component';
import { SconTPai } from 'app/shared/model/scon-t-pai.model';

describe('Component Tests', () => {
    describe('SconTPai Management Detail Component', () => {
        let comp: SconTPaiDetailComponent;
        let fixture: ComponentFixture<SconTPaiDetailComponent>;
        const route = ({ data: of({ sconTPai: new SconTPai(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTPaiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SconTPaiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTPaiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sconTPai).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
