/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTUfeDetailComponent } from 'app/entities/scon-t-ufe/scon-t-ufe-detail.component';
import { SconTUfe } from 'app/shared/model/scon-t-ufe.model';

describe('Component Tests', () => {
    describe('SconTUfe Management Detail Component', () => {
        let comp: SconTUfeDetailComponent;
        let fixture: ComponentFixture<SconTUfeDetailComponent>;
        const route = ({ data: of({ sconTUfe: new SconTUfe(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTUfeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SconTUfeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTUfeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sconTUfe).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
