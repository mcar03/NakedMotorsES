import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EscapesPageComponent } from './escapes-page.component';

describe('EscapesPageComponent', () => {
  let component: EscapesPageComponent;
  let fixture: ComponentFixture<EscapesPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EscapesPageComponent]
    });
    fixture = TestBed.createComponent(EscapesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
