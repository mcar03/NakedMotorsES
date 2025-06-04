import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SobreNosotrosPageComponent } from './sobre-nosotros-page.component';

describe('SobreNosotrosPageComponent', () => {
  let component: SobreNosotrosPageComponent;
  let fixture: ComponentFixture<SobreNosotrosPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SobreNosotrosPageComponent]
    });
    fixture = TestBed.createComponent(SobreNosotrosPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
