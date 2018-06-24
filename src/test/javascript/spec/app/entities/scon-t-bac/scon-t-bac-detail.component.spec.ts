/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTBacDetailComponent } from 'app/entities/scon-t-bac/scon-t-bac-detail.component';
import { SconTBac } from 'app/shared/model/scon-t-bac.model';

describe('Component Tests', () => {
    describe('SconTBac Management Detail Component', () => {
        let comp: SconTBacDetailComponent;
        let fixture: ComponentFixture<SconTBacDetailComponent>;
        const route = ({ data: of({ sconTBac: new SconTBac(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTBacDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SconTBacDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTBacDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sconTBac).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
