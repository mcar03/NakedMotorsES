import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MotosPageComponent } from './motos-page.component';

describe('MotosPageComponent', () => {
  let component: MotosPageComponent;
  let fixture: ComponentFixture<MotosPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MotosPageComponent]
    });
    fixture = TestBed.createComponent(MotosPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
