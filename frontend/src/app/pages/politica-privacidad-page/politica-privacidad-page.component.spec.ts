import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoliticaPrivacidadPageComponent } from './politica-privacidad-page.component';

describe('PoliticaPrivacidadPageComponent', () => {
  let component: PoliticaPrivacidadPageComponent;
  let fixture: ComponentFixture<PoliticaPrivacidadPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PoliticaPrivacidadPageComponent]
    });
    fixture = TestBed.createComponent(PoliticaPrivacidadPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
